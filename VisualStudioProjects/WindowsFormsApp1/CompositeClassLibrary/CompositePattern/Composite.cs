using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    internal class Composite : IComponent, IComposite, IEnumerable<IComponent>
    {
        public string Name { get; set; }

        private List<IComponent> _children = new List<IComponent>();

        public Composite(string name)
        {
            Name = name;
        }

        #region Composite

        public void SortComponents()
        {
            _children.Sort((a, b) => { return (a.GetType().Name.CompareTo(b.GetType().Name)); });
        }

        //private void CalculatePosition(int width, int resolutionInPixels)
        //{

        //}

        #endregion

        #region IComponent
        public void Add(IComponent component)
        {
            _children.Add(component);
        }

        //public void Insert(IComponent component, int index = -1)
        //{
        //    if(index == -1)
        //    {
        //        _children.Add(component);
        //    }
        //    else
        //    {
        //        _children.Insert(index, component);
        //    }
        //}

        public void Remove(IComponent component)
        {
            //_children.Remove(component);
            _children.Clear();
        }

        //public IComponent GetChild(int index)
        //{
        //    return _children[index];
        //}

        public void Draw(Graphics graphics)
        {
            Pen myPen;
            myPen = new Pen(Color.Black);

            //graphics.DrawLine(myPen, 1, ,, l.EndX, y);
            System.Drawing.Font font = new Font("TimesNewRoman", 12);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 1);

            foreach (IComponent component in _children)
            {
                if(component is IComposite)
                {
                    ((IComposite)component).CalculatePosition(0,0);
                }                

                component.Draw(graphics);
            }
        }

        //public override void Display(int depth)
        //{
        //    Console.WriteLine(new String('-', depth) + name);

        //    // Recursively display child nodes

        //    foreach (Component component in _children)
        //    {
        //        component.Display(depth + 2);
        //    }
        //}

        #endregion


        #region IComposite
        public DateTime GetMaxDateTime()
        {
            throw new NotImplementedException();
        }

        public DateTime GetMinDateTime()
        {
            throw new NotImplementedException();
        }

        public void CalculatePosition(int width, int resolutionInPixels)
        {
            var dd = _children[0];
        }
        #endregion

        #region IEnumerable
        public IEnumerator<IComponent> GetEnumerator()
        {
            foreach (IComponent child in _children)
            {
                yield return child;
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        #endregion

    }
}