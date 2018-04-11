using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary
{
    public class Composition : IComponent
    {
        public string Name { get; set; }

        private Composite _compositeRoot;

        public Composition(string name, Compositor compositor) 
        {
            _compositeRoot = new Composite(name);
            Name = name;
        }

        public void Add(IComponent component)
        {
            _compositeRoot.Add(component);            
        }

        public void Draw(Graphics graphics)
        {

            //components.ForEach(a => a.Draw(graphics));
            _compositeRoot.Draw(graphics);
        }

        public void Remove(IComponent component)
        {
            throw new NotImplementedException();
        }

        public IComponent GetChild(int index)
        {
            throw new NotImplementedException();
        }






        #region Compositor // TODO: udskift med compositor - one line (Composite) per objecttype

        public void Compositor_Compose(int width) 
        {
            IComponent composedRoot = Compositor_GroupByTypeInComposites();

            _compositeRoot.Remove(null);

            Add(composedRoot);

            GetMinMaxDateTime(composedRoot);

            var rr = ResolutionUnit(width);
        }



        private IIntervalComponent _componentMinDateTime;
        private IIntervalComponent _componentMaxDateTime;
        private void GetMinMaxDateTime(IComponent component)
        {
            Composite composite;
            composite = component as Composite;

            if (composite != null)
            {
                foreach (IComponent c in composite)
                {
                    if (c is IComposite)
                    {
                        GetMinMaxDateTime(c);
                    }

                    IIntervalComponent ic = c as IIntervalComponent;
                    if (ic != null && ic.Interval != null)
                    {
                        if (_componentMinDateTime == null)
                        {
                            _componentMinDateTime = ic;
                        }
                        else if(ic.Interval.StartDateTime < _componentMinDateTime.Interval.StartDateTime)
                        {
                            _componentMinDateTime = ic;
                        }
                        if (_componentMaxDateTime == null)
                        {
                            _componentMaxDateTime = ic;
                        }
                        else if (ic.Interval.EndDateTime > _componentMaxDateTime.Interval.EndDateTime)
                        {
                            _componentMaxDateTime = ic;
                        }
                    }
                }
            }
        }
        

        private long ResolutionUnit(int width)
        {
            var minTicks = _componentMinDateTime.Interval.StartDateTime.Ticks;
            var maxTicks = _componentMaxDateTime.Interval.EndDateTime.Value.Ticks; // TODO: null value??
            var ticksPerPixel = (maxTicks - minTicks) / width;
            return ticksPerPixel;
        }


        private Composite Compositor_GroupByTypeInComposites()
        {
            _compositeRoot.SortComponents();

            Composite composedRoot = null;
            Composite currentLine = null;
            IComponent prevComponent = null;
            int cnt = 0;
            foreach (IComponent component in _compositeRoot)
            {
                if (prevComponent?.GetType() != component.GetType())
                {
                    if (composedRoot == null)
                    {
                        composedRoot = new Composite("ComposedRoot");
                    }
                    else
                    {
                        composedRoot.Add(currentLine);
                    }
                    currentLine = new Composite("CompositeLine_" + cnt);
                    cnt++;
                }

                currentLine.Add(component);
                prevComponent = component;
            }
           

            composedRoot.Add(currentLine);

            return composedRoot;
        }

        #endregion
    }
}
